(ns problems.83)

(defn __ [& b] (if (and (some identity b) (not (every? identity b))) true false))

(__ false false)
