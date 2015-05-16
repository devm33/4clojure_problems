(ns problems.150)

(defn __ [start]
  (letfn [(numseq [n] (map #(read-string (str %)) (str n)))
          (palin? [s] (if (empty? s) true
                        (when (= (first s) (last s))
                          (recur (butlast (rest s))))))]
    (lazy-seq
      (if (palin? (numseq start))
        (cons start (__ (inc start)))
        (__ (inc start))))))

(take 6 (__ 12345660000))

