# Effects Package

The `effects` package provides effect-oriented content types. At the moment it includes particle support.

## What Is Available

- `particles/ModParticle`: A simple `SimpleParticleType` implementation with optional limiter override.

## Quick Start

```java
import com.github.syren_dev_tech.scylla.effects.particles.ModParticle;

var ashParticle = new ModParticle();
var heavyAshParticle = new ModParticle(true);
```

Use your platform-specific particle registrar to register these particle instances.
