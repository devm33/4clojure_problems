(ns problems.110)

; pronounciations of numbers
; infinite lazy seq

(defn __ [coll]
  (let [pron (mapcat #(vector (count %) (first %))
                     (partition-by identity coll))]
    (cons pron (lazy-seq (__ pron)))))

(take 3 (__ [1]))
