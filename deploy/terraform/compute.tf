resource "scaleway_instance_ip" "public_ip" {}

resource "scaleway_instance_security_group" "sg" {
  name = "sg-jee"

  inbound_default_policy  = "drop"
  outbound_default_policy = "accept"

  inbound_rule {
    action = "accept"
    port   = "22"
  }

  inbound_rule {
    action = "accept"
    port   = "8080"
  }
}

resource "scaleway_instance_server" "www-01" {
  name        = "jee-01"
  type        = "DEV1-S"
  image       = "debian_buster"
  enable_ipv6 = true

  ip_id             = scaleway_instance_ip.public_ip.id
  security_group_id = scaleway_instance_security_group.sg.id
}

output "ipv4" {
  value = scaleway_instance_server.www-01.public_ip
}
output "ipv6" {
  value = scaleway_instance_server.www-01.ipv6_address
}
