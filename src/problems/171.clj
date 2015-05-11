(ns problems.171)

(defn __ [s]
  (if (empty? s) []
    (let [[f & r] (sort (distinct s))]
      (reduce
        (fn [s v]
          (let [p (last (last s))]
            (if (= p v) s
              (if (= (inc p) v)
                (assoc-in s [(dec (count s)) 1] v)
                (conj s [v v])))))
        [[f f]] r))))

(assert (= (__ [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])
           [[1 4] [6 6] [9 11] [13 17] [19 19]]))

(assert (= (__ []) []))

