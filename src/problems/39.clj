(ns problems.39)

(defn interleave2 [xs ys]
  (if (some empty? [xs ys])
    (empty xs)
    (cons
      (first xs)
      (cons
        (first ys)
        (interleave2 (rest xs) (rest ys))))))

;(interleave2 '(1 3 5) '(2 4 6))

(assert (= (interleave2 '(1 3 5) '(2 4 6)) '(1 2 3 4 5 6)))

(assert (= (interleave2 [1 3 5] [2 4 6]) [1 2 3 4 5 6]))


; way better solution!

(mapcat list [1 3 5] [2 4 6])