(ns io.pedestal.service.http.http-kit
  (:import (org.httpkit.server AsyncChannel HttpServer RingHandler)))

(defn- create-handler-and-server
  "Create an http-kit server from a ring handler and options."
  [handler {:keys [port thread ip max-body max-line worker-name-prefix queue-size]
            :or   {ip "0.0.0.0"  ; which ip (if has many ips) to bind
                   port 8090     ; which port listen incomming request
                   thread 4      ; http worker thread count
                   queue-size 20480 ; max job queued before reject to project self
                   worker-name-prefix "worker-" ; woker thread name prefix
                   max-body 8388608             ; max http body: 8m
                   max-line 4096}}]  ; max http inital line length: 4K
  (let [h (RingHandler. thread handler worker-name-prefix queue-size)]
    [h (HttpServer. ip port h max-body max-line)]))

(defn server
  ([handler] (server servlet {}))
  ([handler options]
   (let [[ring-handler server] (create-server-handler-and-server handler
                                                                 options)]
     {:server server
      :start-fn (fn [] (.start server))
      :stop-fn (fn []
                 (.close ring-handler)
                 (.stop server))})))
