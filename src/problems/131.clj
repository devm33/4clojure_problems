(ns problems.131)

(defn __ [& sets]
  (letfn [(power [s]
            (if (empty? s) #{#{}} (let [sub (power (rest s))]
                (apply conj sub (map #(conj % (first s)) sub)))))]
    (->> sets
      (map #(set (map (partial reduce +) (disj (power %) #{}))))
      (apply clojure.set/intersection)
      (empty?)
      (not))))


(assert (__ #{-1 1 99}
            #{-2 2 888}
            #{-3 3 7777}))
(assert (= false (__ #{1}
                     #{2}
                     #{3}
                     #{4})))
