(ns problems.140)

(defn __ [eqs]
  (let
    [notmap (#(reduce (fn [m [k v]] (assoc m v k)) % %)
                      '{a A b B c C d D})
     oneoff? #(let [a (apply disj %1 %2) b (apply disj %2 %1)]
                (when (and (= 1 (count a) (count b)) (= (notmap (first a)) (first b)))
                  (disj %1 (first a))))
     combine (fn [sets]
               (loop [s (seq sets) out sets]
                 (if (empty? s) out
                   (recur
                     (rest s)
                     (let [s1 (first s)]
                       (reduce
                         #(if-let [c (oneoff? s1 %2)] (conj (disj %1 s1 %2) c)
                            %1)
                         out (rest s)))))))
     converge (fn [f v] (let [nv (f v)] (if (= v nv) nv (recur f nv))))
     covers? (fn [sets]
               (every? (fn [s] (some #(empty? (apply disj % s)) sets)) eqs))
     minimize (fn [sets]
                (let [covering-sets (for [s (seq sets)
                                          :let [sets-s (disj sets s)]
                                          :when (covers? sets-s)]
                                      sets-s)]
                  (case (count covering-sets)
                    0 sets
                    1 (first covering-sets)
                    (apply min-key count covering-sets))))]
    (minimize (converge combine eqs))))

(assert (= (__ #{#{'a 'B 'C 'd}
                 #{'A 'b 'c 'd}
                 #{'A 'b 'c 'D}
                 #{'A 'b 'C 'd}
                 #{'A 'b 'C 'D}
                 #{'A 'B 'c 'd}
                 #{'A 'B 'c 'D}
                 #{'A 'B 'C 'd}})
           #{#{'A 'c} 
             #{'A 'b}
             #{'B 'C 'd}}))

(assert (= (__ #{#{'A 'B 'C 'D}
                 #{'A 'B 'C 'd}})
           #{#{'A 'B 'C}}))

(assert (= (__ #{#{'a 'b 'c 'd}
                 #{'a 'B 'c 'd}
                 #{'a 'b 'c 'D}
                 #{'a 'B 'c 'D}
                 #{'A 'B 'C 'd}
                 #{'A 'B 'C 'D}
                 #{'A 'b 'C 'd}
                 #{'A 'b 'C 'D}})
           #{#{'a 'c}
             #{'A 'C}}))
