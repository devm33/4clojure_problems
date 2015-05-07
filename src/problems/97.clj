(ns problems.97)

(defn __ [n]
  ; return the nth row (1-based) of pascal's triangle
  (reductions
    (fn [prev k]
      (* prev (/ (- n k) k)))
    1 ; n choose 0
    (range 1 n)))

(assert (= (__ 1)  [1]))

(assert (= (map __ (range 1 6))
   [     [1]
        [1 1]
       [1 2 1]
      [1 3 3 1]
     [1 4 6 4 1]]))

(assert (= (__ 11)
           [1 10 45 120 210 252 210 120 45 10 1]))
