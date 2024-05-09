val test = """^((#{1,5} )(.*))$""".r

test.findFirstMatchIn("## Prep").map(_.group(1)).mkString
test.findFirstMatchIn("# Prep").map(_.group(1)).mkString
test.findFirstMatchIn("Amulet Troubles (Quest)#Ciaran (Player) Felix talks with Vivec (NPC)]]").map(_.group(1)).mkString