(ns problems.58)

(def mycomp
  (fn [ & fns ]
    (fn [ & args ]
      (reduce
        #(%2 %1)
        (apply (last fns) args)
        (rest (reverse fns)))))
  )


((mycomp rest reverse) [1 2 3 4])