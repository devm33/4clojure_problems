(ns problems.38)

(defn mymax [& col]
  (reduce #(if (> %1 %2) %1 %2) col))

(mymax 32 4 2 7)