(ns problems.62)

(defn __ [f x] (cons x (lazy-seq (__ f (f x)))))

(assert (= (take 5 (__ #(* 2 %) 1)) [1 2 4 8 16]))
(assert (= (take 100 (__ inc 0)) (take 100 (range))))
(assert (= (take 9 (__ #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3]))))
