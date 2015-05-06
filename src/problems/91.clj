(ns problems.91)

; undirected connected graph
; (lifting this from 89)

(defn connected? [adjlist]
  (if (<= (count adjlist) 1)
    true
    (loop [conn (set (first adjlist)) xs (rest adjlist)]
      (if (empty? xs)
        true
        (let [cut ((juxt filter remove) #(some (partial contains? conn) %) xs)]
          (if (empty? (first cut))
            false
            (recur (reduce (partial apply conj) conn (first cut)) (second cut))))))))

(def __ connected?)




(assert (= true (__ #{[:a :a]})))

(assert (= true (__ #{[:a :b]})))

(assert (= false (__ #{[1 2] [2 3] [3 1]
               [4 5] [5 6] [6 4]})))

(assert (= true (__ #{[1 2] [2 3] [3 1]
              [4 5] [5 6] [6 4] [3 4]})))

(assert (= false (__ #{[:a :b] [:b :c] [:c :d]
               [:x :y] [:d :a] [:b :e]})))

(assert (= true (__ #{[:a :b] [:b :c] [:c :d]
              [:x :y] [:d :a] [:b :e] [:x :a]})))
