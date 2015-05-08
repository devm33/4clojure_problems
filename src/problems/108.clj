(ns problems.108)

; find the smallest number common in all lists

(defn __ [& colls]
  (let [firsts (map first colls)]
    (if (apply = firsts)
      (ffirst colls)
      (let [m (apply min firsts)]
        (apply __ (map #(if (= m (first %)) (next %) %) colls))))))


(assert (= 3 (__ [3 4 5])))
(assert (= 4  (__ [1 2 3 4 5 6 7]  [0.5 3/2 4 19])))
(assert (= 7 (__ (range) (range 0 100 7/6) [2 3 5 7 11 13])))
(assert (= 64 (__ (map #(* % % %) (range)) ;; perfect cubes
                  (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
                  (iterate inc 20)))) ;; at least as large as 20
