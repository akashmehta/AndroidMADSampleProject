import {fail, warn, message, markdown, danger} from "danger"

fail("this is failure message")
warn("this is warning")
message("This is normal message")
markdown("*Markdown* is also **Supported**")

const {additions = 0, deletions = 0} = danger.github.pr
message (`:tada: The PR added ${additions} and removed ${deletions} lines.`)

if !(github.pr_body + github.pr_title).include?("AMS")
    warn("Please enter valid message")
end