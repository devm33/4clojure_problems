(ns problems.44)

(defn rotate [n coll]
  (if (= n 0)
    coll
    (when-let [s (seq coll)]
      (if (> n 0)
        (rotate (dec n) (concat (rest s) [(first s)]))
        (rotate (inc n) (conj (drop-last s) (last s)))
        )
      )
    )
  )


(= 
  
  (rotate 2 [1 2 3 4 5]) 
  
  '(3 4 5 1 2))