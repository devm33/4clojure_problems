(ns problems.114)

(defn __ [n p s]
  (let [f (first s) f? (p f) nn (if f? (dec n) n)]
    (when (> nn 0)
      (lazy-seq (cons f (__ nn p (rest s)))))))


(__ 4 #(= 2 (mod % 3)) [2 3 5 7 11 13 17 19 23])
