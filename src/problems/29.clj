(ns problems.29)

(defn getcaps [s]
  (apply str (filter #(Character/isUpperCase %) s)))

(getcaps "PASSfail")