resource "random_password" "admin" {
  length  = 16
  special = true
}

variable "RDB_PASS" {
  type = string
}
resource "scaleway_rdb_user" "api" {
  instance_id = scaleway_rdb_instance.main.id
  name        = "api"
  password    = var.RDB_PASS
  is_admin    = true
}

resource "scaleway_rdb_instance" "main" {
  name           = "beanz-rdb-postgres"
  node_type      = "db-dev-s"
  engine         = "PostgreSQL-11"
  is_ha_cluster  = false
  disable_backup = true
  user_name      = "admin"
  password       = random_password.admin.result
}


output "rdb_ip" {
  value = scaleway_rdb_instance.main.endpoint_ip
}

output "rdb_port" {
  value = scaleway_rdb_instance.main.endpoint_port
}
