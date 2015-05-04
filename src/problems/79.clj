(ns problems.79)

(defn __ [tri]
  (first
    (reduce
      (fn [sum row]
        (map-indexed
          (fn [i v]
            (+ v (min (nth sum i) (nth sum (inc i)))))
          row))
      (reverse tri))))



(assert (= 7 (__ '([1]
                   [2 4]
                   [5 1 4]
                   [2 3 4 5])))) ; 1->2->1->3

(assert (= 20 (__ '([3]
                    [2 4]
                    [1 9 3]
                    [9 9 2 4]
                    [4 6 6 7 8]
                    [5 7 3 5 1 4])))) ; 3->4->3->2->7->1
