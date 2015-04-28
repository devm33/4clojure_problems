(ns problems.53)

; longest increasing sub-sequence

;(def example [1 2 0 1 2 3 0 4 5])
;(def example [7 6 5 4])
;(def example [5 6 1 3 2 7])
(def example [2 3 3 4 5])

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

(defn mxncr [coll]
  (let
    [maxsub (apply max-key count
    (reverse (loop [ s (seq coll) res [] ]
      (if (empty? s)
        res
        (let
          [group
           (loop [xs (next s) cur [(first s)]]
             (if (or (empty? xs) (>= (last cur) (first xs)))
               [xs cur]
               (recur (rest xs) (conj cur (first xs)))))]
          (recur (first group) (conj res (second group))))))))]
    (if (< (count maxsub) 2) [] maxsub)))


(println (str "final " (mxncr example)))