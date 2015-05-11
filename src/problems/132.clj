(ns problems.132)

(defn __ [pred v s]
  (when (not (empty? s))
    (lazy-seq
      (let [s0 (first s) s1 (second s)]
        (concat (if (and (not (nil? s1)) (pred s0 s1)) [s0 v] [s0])
                (__ pred v (next s)))))))

(assert (= '(1 :less 6 :less 7 4 3) (__ < :less [1 6 7 4 3])))

(assert (= [0 1 :same 1 2 3 :same 5 8 13 :same 21]
   (take 12 (->> [0 1]
                 (iterate (fn [[a b]] [b (+' a b)]))
                 (map first) ; fibonacci numbers
                 (__ (fn [a b] ; both even or both odd
                       (= (mod a 2) (mod b 2)))
                     :same)))))
