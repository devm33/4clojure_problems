(ns problems.30)

(defn dedupe [col]
  (reduce
    #(if (= (last %1) %2)
       %1
       (concat %1 [%2]))
    (list (first col))
    col))
    
(= (dedupe [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))