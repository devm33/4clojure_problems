(ns problems.28)

(defn flat [col]
  (if (empty? col)
    col
    (let
      [x (first col)
       xs (rest col)]
      (if (coll? x)
        (concat (flat x) (flat xs))
        (cons x (flat xs))))))

(flat '((1 2) 3 [4 [5 6]]))