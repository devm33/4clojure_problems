(ns problems.41)

(defn drop_nth [xs n]
  (mapcat (partial take (dec n)) (partition-all n xs)))

(assert (= (drop_nth '(1 2 0 3 4 0 5 6) 3)) '(1 2 3 4 5 6))


; slightly better solution
; uses step argument to partition-all
(
  #(apply concat (partition-all (dec %2) %2 %1))
  '(1 2 0 3 4 0 5 6)
  3)