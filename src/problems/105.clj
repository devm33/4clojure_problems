(ns problems.105)

(defn __ [coll]
  (loop [s (seq coll) m {} kw nil]
    (if (empty? s) m
      (let [f (first s) k? (keyword? f)]
        (recur (rest s)
               (if k?
                 (assoc m f [])
                 (update-in m [kw] conj f))
               (if k? f kw))))))



(__ [:a 1 2 3 :b :c 4])
