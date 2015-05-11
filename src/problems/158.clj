(ns problems.158)

(defn __ [f]
  (fn [& args]
    (reduce #(%1 %2) f args)))

(= 10 ((__ (fn [a]
             (fn [b]
               (fn [c]
                 (fn [d]
                   (+ a b c d))))))
       1 2 3 4))
