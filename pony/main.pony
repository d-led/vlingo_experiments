actor Main
  new create(env: Env) =>
    let thing = Thing(env)
    thing.jump()

actor Thing
  let _env : Env

  new create(env: Env) =>
    _env = env

  be jump() =>
    _env.out.print("jumping...")
