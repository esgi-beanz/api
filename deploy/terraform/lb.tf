resource "scaleway_lb_ip" "ip" {
}

resource "scaleway_lb" "lb01" {
  name   = "jee-lb01"
  ip_id  = scaleway_lb_ip.ip.id
  region = "fr-par"
  type   = "LB-S"
}

resource "scaleway_lb_backend" "backend01" {
  lb_id            = scaleway_lb.lb01.id
  name             = "jee-backend01"
  forward_protocol = "http"
  forward_port     = "8080"
  server_ips       = [scaleway_instance_server.www-01.public_ip]
}

resource "scaleway_lb_frontend" "frontend01" {
  name         = "jee-frontend01"
  lb_id        = scaleway_lb.lb01.id
  backend_id   = scaleway_lb_backend.backend01.id
  inbound_port = "80"
}
