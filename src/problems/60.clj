(ns problems.60)

(defn rdctns
  ([f xs]
   (lazy-seq
     (when-let [s (seq xs)]
       (rdctns f (first s) (next s)))))
  ([f v xs]
   (cons v
         (lazy-seq
           (when-let [s (seq xs)]
             (rdctns f (f v (first s)) (rest s)))))))

(println (take 5 (reductions + (range))))
(println (take 5 (rdctns + (range))))
