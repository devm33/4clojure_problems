(ns problems.53)

; longest increasing sub-sequence

(def example [1 0 1 2 3 0 4 5])
;(def example [7 6 5 4])

(defn takeincr [coll]
  (if (empty? coll)
    []
    (if (and (next coll) (< (first coll) (second coll)))
      (cons (first coll) (takeincr (rest coll)))
      [(first coll)])))

(println (takeincr example))

(defn allincr [coll]
  (when (not-empty coll)
    (let [fst (takeincr coll)]
      (cons fst (allincr (drop (count fst) coll))))))

(println (allincr example))

(defn maxincr [coll]
  (let [ms (apply max-key count (allincr coll))]
    (if (< (count ms) 2) [] ms)))

(println (maxincr example))

; now mash it all together for 4clojure.com

(defn takeallincr [coll]
  (when-let [s (seq coll)]
    (loop [xs (rest s) cur [(first s)]]
      (if (empty? xs)
        cur
        (if (< (last cur) (first xs))

(println (mxncr example))