(ns problems.33)

(defn repf [col num]
  (reduce #(concat %1 (repeat num %2)) '() col))


(repf '(1 2 3) 4)


; way better solution!

(defn better_repf [col num]
  (mapcat (partial repeat num) col))

(better_repf '(1 2 3) 4)