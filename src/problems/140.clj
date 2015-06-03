(ns problems.140)

(def f-test #{#{'a 'B 'C 'd}
              #{'A 'b 'c 'd}
              #{'A 'b 'c 'D}
              #{'A 'b 'C 'd}
              #{'A 'b 'C 'D}
              #{'A 'B 'c 'd}
              #{'A 'B 'c 'D}
              #{'A 'B 'C 'd}})

(defn __ [eqs]
  (let [notmap (#(reduce (fn [m [k v]] (assoc m v k)) % %)
                         '{a A b B c C d D})
        combine (memoize
                  (fn [s1 s2]
                    (reduce
                      (fn [res item]
                        (if (contains? res (notmap item))
                          (disj res item (notmap item))
                          (conj res item)))
                      #{} (apply conj s1 s2))))
        oneoff? (fn [s1 s2]
                 (let [c (combine s1 s2)]
                   (when (< (count c) (min (count s1) (count s1))) c)))
        ]
    (loop [in eqs]
      (let
        [nin
         (loop [s (seq in) out #{}]
           (if (empty? s) out
             (recur
               (rest s)
               (let
                 [combined
                  (->> (rest s) (map #(oneoff? (first s) %)) (filter nil?))]
                 (if (empty? combined)
                   (conj out (first s))
                   (apply conj out combined))))))]
        (if (= (count in) (count nin)) nin
          (recur nin))))))

(__ f-test)
