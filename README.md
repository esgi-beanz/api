# Beanz API

Asset portfolio management API.

## Setup

Copy `.env.example` to `.env` and fill in the values.

Build the Docker image:

```sh
docker-compose build --no-cache
```

Start the API and the database:

```sh
docker-compose up -d
```

## Routes

When running the API go to `/swagger-ui/index.html`.

## Deployment

The API can be deployed on a HA infrastructure from scratch trough a manuel GitHub action workflow.

The workflow will do the following:

- Deploy the infrastructure on Scaleway with Terraform:
  - Manage PostgresSQL database
  - Container Registry
  - Managed Kubernetes cluster with 2+ nodes and autoscaling
- Build the API into a Docker image
- Push the image to the Container Registry
- Setup the DB/User on the managed PostgreSQL instance
- Deploy the API to the k8s cluster:
  - Setup `kubctl` and `scw`, setup the kubeconfig
  - Create a namespace for the API
  - Create secrets containing the DB stuff
  - Create a deployment with multiple pods of the API
  - Create a service pointing the pods
  - Create a Nginx ingress controller pointing to the service
  - Create a managed Load Balancer pointing the ingress controller

The Terraform state is stored on an Object Storage bucket at Scaleway.

There is also a manual action to delete the entire thing with `terraform destroy`.
