(ns problems.112)

(defn __ [m xs]
  (loop [sum 0 s (seq xs) r []]
    (if (empty? s) r
      (let [f (first s) fret (if (coll? f) (__ (- m sum) f) f)
            fval (if (coll? fret) (reduce + (flatten fret)) fret)]
        (if (> (+ fval sum) m) r
          (recur (+ fval sum) (next s) (conj r fret)))))))


(assert (=  (__ 10 [1 2 [3 [4 5] 6] 7])
   '(1 2 (3 (4)))))

(assert (=  (__ 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
   '(1 2 (3 (4 (5 (6 (7))))))))

(assert (=  (__ 9 (range))
   '(0 1 2 3)))

(assert (=  (__ 1 [[[[[1]]]]])
   '(((((1)))))))

(assert (=  (__ 0 [1 2 [3 [4 5] 6] 7])
   '()))

(assert (=  (__ 0 [0 0 [0 [0]]])
   '(0 0 (0 (0)))))

(assert (=  (__ 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
   '(-10 (1 (2 3 (4))))))
