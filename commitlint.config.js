/*
 * commitlint.config.js
 * Copyright (C) 2021  <@localhost>
 *
 * Distributed under terms of the MIT license.
 */
module.exports = {
    extends: [
        "@commitlint/config-conventional"
    ],
  rules: {
        "type-enum": [2, "always", ["build", "chore", "ci", "docs", "feat", "fix", "perf", "refactor", "revert", "style", "test"]],
    }
}
