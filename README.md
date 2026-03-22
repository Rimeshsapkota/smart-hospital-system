# smart-hospital-system

Mono<T>

1 item → container has something inside it
example: Mono<UserResponse>
container holds one UserResponse object

0 item → container is empty
example: Mono.empty()
no result inside — operation had nothing to return

Error  → container caught an exception
example: Mono.error(new AlreadyExistException())
not empty, not a value — something went wrong

    "error:": "No HandlerResultHandler for MonoFlatMapMany"

    - "8762:8761"   # ✅ Mac port 8762 → container port 8761
                      # local uses 8761
                      # Docker uses 8762

Service             Local (IntelliJ)    Docker (browser)
──────────────────────────────────────────────────────
discovery-server    localhost:8761      localhost:9761
config-service      localhost:8888      localhost:9888
auth-service        localhost:8144      localhost:9144
api-gateway         localhost:8020      localhost:9020
mysql               localhost:3306      localhost:3307

