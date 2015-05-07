(ns problems.103)


(defn __ [n s]
  (if (or (= n 0) (empty? s) (> n (count s)))
    #{}
    (if (= 1 n)
      (set (map hash-set s))
      (when-let [xs (seq s)]
        (set (mapcat
               (fn [i]
                 (let [ni (nth xs i)]
                   (let [sbs (__ (dec n) (drop (inc i) xs))]
                     (map (fn [nxs] (conj nxs ni)) sbs))))
               (range (- (count xs) (dec n)))))))))


(assert (= (__ 1 #{4 5 6}) #{#{4} #{5} #{6}}))

(assert (= (__ 10 #{4 5 6}) #{}))

(assert (= (__ 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}}))

(assert (= (__ 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                         #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}}))

(assert (= (__ 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}}))

(assert (= (__ 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                    #{:a "abc"} #{:a "efg"} #{"abc" "efg"}}))
