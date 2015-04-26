(ns problems.32)

(defn dupe [col]
  (if (empty? col)
    col
    (cons (first col) (cons (first col) (dupe (rest col))))))

(assert (= (dupe [1 2 3]) '(1 1 2 2 3 3)))

(assert (= (dupe [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))

(assert (= (dupe [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))
