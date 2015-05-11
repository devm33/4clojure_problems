(ns problems.131)

(fn [& sets]
  (letfn [(power [s] (let [sub (power (rest s))] (apply conj sub (map #(conj % (first s)) sub)))))]
    
