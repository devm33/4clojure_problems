(ns problems.144)

(defn osc [v & fns]
  (lazy-seq (cons v (apply osc ((first fns) v) (next (cycle fns))))))

(def __ osc)

(take 3 (__ 3.14 int double))
