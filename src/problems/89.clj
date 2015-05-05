(ns problems.89)

; Eulerian trail
; - can start at any node
; - must visit each edge exactly once
; - all edges are undirected
; return true if possible


; by this result https://proofwiki.org/wiki/Condition_for_Graph_to_be_Traversabl
; we need only check if the graph is connected and has at most 2 odd vertices

(defn __ [adjlist]
  (if (<= (count adjlist) 1)
    true
    (let
      [is-connected?
       (loop [conn (set (first adjlist)) xs (rest adjlist)]
         (if (empty? xs)
           true
           (let [cut ((juxt filter remove) #(some (partial contains? conn) %) xs)]
             (if (empty? (first cut))
               false
               (recur (reduce (partial apply conj) conn (first cut))
                      (second cut))))))
       odd-degrees
       (filter odd? (vals (reduce #(update-in %1 [%2] (fnil inc 0))
                                  {} (flatten adjlist))))]
      (and is-connected? (< (count odd-degrees) 3)))))

(assert (= true (__ [[:a :b]])))
(assert (= false (__ [[:a :a] [:b :b]])))
(assert (= false (__ [[:a :b] [:a :b] [:a :c] [:c :a]
               [:a :d] [:b :d] [:c :d]])))
(assert (= true (__ [[1 2] [2 3] [3 4] [4 1]])))
(assert (= true (__ [[:a :b] [:a :c] [:c :b] [:a :e]
              [:b :e] [:a :d] [:b :d] [:c :e]
              [:d :e] [:c :f] [:d :f]])))
(assert (= false (__ [[1 2] [2 3] [2 4] [2 5]])))


(defn connected? [adjlist]
  ; return true if undirected graph is a single connected component
  ; assume (>= (count adjlist) 1)
  (loop [conn (set (first adjlist)) xs (rest adjlist)]
    (if (empty? xs)
      true
      (let [cut ((juxt filter remove) #(some (partial contains? conn) %) xs)]
        (if (empty? (first cut))
          false
          (recur (reduce (partial apply conj) conn (first cut)) (second cut)))))))

(assert (= true (connected? [[:a :a]])))
(assert (= false (connected? [[:a :a] [:b :b]])))
(assert (= true (connected? [[:a :b] [:b :c] [:c :a]])))


(defn degrees [adjlist]
  ; return a map of each vertex to its degree
  (reduce #(update-in %1 [%2] (fnil inc 0)) {} (flatten adjlist)))

(assert (= (degrees [[:a :a] [:b :a]]) {:a 3 :b 1}))

