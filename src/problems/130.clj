(ns problems.130)

(defn __ [newroot tree]
  ((fn newroot? [tree parent]
     (if (= newroot (first tree)) (concat tree parent)
       (some (fn [child]
               (newroot? child [(concat (remove #(= child %) tree) parent)]))
             (rest tree))))
   tree nil))

(assert (= '(n) (__ 'n '(n))))

(assert (= '(a (t (e))) (__ 'a '(t (e) (a)))))

(assert (= '(e (t (a))) (__ 'e '(a (t (e))))))
