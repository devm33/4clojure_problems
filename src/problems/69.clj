(ns problems.69)

(defn __ [f m0 & maps]
  (reduce
    (fn [m1 m2]
      (reduce
        (fn [m [k v]]
          (if (contains? m k)
            (assoc m k (f (get m k) v))
            (assoc m k v)))
        m1
        m2))
    m0
    maps
    ))


(assert (= (__ * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
           {:a 4, :b 6, :c 20}))

(assert (= (__ - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15}))

(assert (= (__ concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]}))
