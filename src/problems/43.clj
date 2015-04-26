(ns problems.43)

(defn undointer [coll n]
  (map
    #(take-nth n (nthnext coll %))
    (range n)))


(undointer [1 2 3 4 5 6] 2)


; way smaller solution, using trickiness of map taking multiple lists
; as params (map f c1 c2 c3 & colls) triggered by applying the results
; of partition
; note: would only work when n divides (count xs) since map will stop
; at the end of the shortest coll

(fn [xs n] (apply map vector (partition n xs)))