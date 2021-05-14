resource "scaleway_k8s_cluster" "main" {
  name                        = "beanz-k8s-cluster"
  version                     = "1.21.0"
  cni                         = "flannel"
  delete_additional_resources = true

  autoscaler_config {
    disable_scale_down               = false
    scale_down_delay_after_add       = "1m"
    scale_down_unneeded_time         = "1m"
    scale_down_utilization_threshold = 0.5
  }
}

resource "scaleway_k8s_pool" "main" {
  cluster_id          = scaleway_k8s_cluster.main.id
  name                = "beanz-k8s-pool"
  node_type           = "DEV1-M"
  size                = 2
  autoscaling         = true
  autohealing         = true
  min_size            = 2
  max_size            = 5
  container_runtime   = "containerd"
  wait_for_pool_ready = true
}

output "k8s_apiserver_url" {
  value = scaleway_k8s_cluster.main.apiserver_url
}
output "k8s_wildcard_dns" {
  value = scaleway_k8s_cluster.main.wildcard_dns
}
output "k8s_status" {
  value = scaleway_k8s_cluster.main.status
}
output "k8s_upgrade_available" {
  value = scaleway_k8s_cluster.main.upgrade_available
}

output "k8s_cluster_id" {
  value = scaleway_k8s_cluster.main.id
}
