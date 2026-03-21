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

