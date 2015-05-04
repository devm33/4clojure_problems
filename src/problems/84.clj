(ns problems.84)

(defn __ [s]
  (let [m (apply conj {} s)]
    (reduce
      (fn [r [k v]]
        (if (not (contains? m v))
          r
          (let [nkv [k (get m v)]]
            (recur (conj r nkv) nkv))))
      s m)))


(assert (let [divides #{[8 4] [9 3] [4 2] [27 9]}]
          (= (__ divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]})))


(assert (let [more-legs
              #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
          (= (__ more-legs)
             #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
               ["spider" "cat"] ["spider" "man"] ["spider" "snake"]})))
