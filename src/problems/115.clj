(ns problems.115)

(defn __ [n]
  (let [d (reverse (map #(mod % 10) (take-while (partial < 0) (iterate #(quot % 10) n))))
        c (quot (count d) 2)]
    (apply = (map (partial reduce +) [(take c d) (take-last c d)]))))



(assert (= true (__ 11)))

(assert (= true (__ 121)))

(assert (= false (__ 123)))

(assert (= true (__ 0)))

(assert (= false (__ 88099)))

(assert (= true (__ 89098)))

(assert (= true (__ 89089)))

(assert (= (take 20 (filter __ (range)))
   [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101]))
