(ns problems.63)

(defn __ [f s]
  (reduce
    (fn [m v]
      (let [k (f v)]
        (assoc m k (conj (get m k []) v))))
    {} s))

(assert (= (__ #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]}))

(assert (= (__ #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]}))

(assert (= (__ count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}))
