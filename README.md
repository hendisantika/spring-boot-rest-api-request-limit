# spring-boot-rest-api-request-limit

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-rest-api-request-limit/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-rest-api-request-limit/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-brightgreen?logo=springboot)

REST API rate limiting demo with Spring Boot, [Bucket4j](https://bucket4j.com/) and Hazelcast.

## Tech Stack

| Component            | Version                         |
|----------------------|---------------------------------|
| Java                 | 25 (Temurin in CI)              |
| Spring Boot          | 4.1.2                           |
| Bucket4j             | 8.10.1 (+ Spring Boot starter)  |
| Hazelcast            | via `spring-boot-starter-hazelcast` |
| Build tool           | Maven Wrapper (Maven 3.10.0)    |

## Requirements

- JDK 25
- No local Maven needed: use the bundled `./mvnw`

## Build & Run

```shell
./mvnw clean package   # compile + run tests
./mvnw spring-boot:run # start on http://localhost:8080
```

Actuator/Prometheus metrics are exposed at `http://localhost:8080/actuator`.

## Endpoints

| Method | Path                 | Rate limited by                         |
|--------|----------------------|-----------------------------------------|
| GET    | `/api/v1/names`      | Global Bucket4j filter (1000 req/s)     |
| POST   | `/api/v1/names`      | Global Bucket4j filter (1000 req/s)     |
| GET    | `/api/v1/rt/api-key` | `X-Api-Key` header, per pricing plan    |
| GET    | `/api/v1/rt/ip`      | Client IP address                       |

### Pricing plans (refilled every hour)

| Plan         | Requests / hour | Demo API key                           |
|--------------|-----------------|----------------------------------------|
| FREE         | 20              | `d0476978-free-4ad5-94e8-38ebb575f5c4` |
| BASIC        | 40              | `d0476978-base-4ad5-94e8-38ebb575f5c5` |
| PROFESSIONAL | 100             | `d0476978-prof-4ad5-94e8-38ebb575f5c6` |

## CI/CD & Dependency Updates

- **GitHub Actions** (`.github/workflows/maven.yml`): builds and tests with JDK 25 on every push / PR to `main`,
  and submits the Maven dependency graph to GitHub on pushes.
- **Dependabot** (`.github/dependabot.yml`): daily at 05:00 Asia/Jakarta for Maven and GitHub Actions.
  Related updates are grouped (Spring, Bucket4j, Hazelcast, Maven plugins, GitHub Actions), labelled, and use
  conventional commit prefixes (`build(deps)`, `ci(deps)`).

## Background

What is Rate Limiting?
[Rate limiting](https://www.cloudflare.com/en-gb/learning/bots/what-is-rate-limiting/) is a strategy
to [limit access to APIs](https://cloud.google.com/solutions/rate-limiting-strategies-techniques). It restricts the
number of API calls that a client can make within a certain time frame. This helps defend the API against overuse, both
unintentional and malicious.
It puts a cap on how often someone can repeat an action within a certain timeframe — for instance, trying to log in to
an account. Rate limiting can help stop certain kinds of
malicious [bot activity](https://www.cloudflare.com/learning/bots/what-is-a-bot/). Rate limits are often applied to an
API by tracking the IP address, or in a more business-specific way, such as API keys or access tokens.

### What is Bucket4j?

[Bucket4j](https://bucket4j.com/8.7.0/toc.html) is a Java rate-limiting library that is mainly based on the token-bucket
algorithm, which is by the de-facto standard for rate-limiting in the IT industry.

What is Token Bucket Algorithm?
The Token Bucket algorithm is a popular and simple method used in computer networking and telecommunications for traffic
shaping and rate limiting. It is designed to control the amount of data that a system can send or receive in some sort
of period, ensuring that the traffic conforms to a specified rate.

The Apache benchmark tool is very basic, and while it will give you a solid idea of some performance, it is a bad idea
to only depend on it if you plan to have your site exposed to serious stress in production.

Having said that, here's the most common and simplest parameters:

-c: ("Concurrency"). Indicates how many clients (people/users) will be hitting the site at the same time. While ab runs,
there will be -c clients hitting the site. This is what actually decides the amount of stress your site will suffer
during the benchmark.

-n: Indicates how many requests are going to be made. This just decides the length of the benchmark. A high -n value
with a -c value that your server can support is a good idea to ensure that things don't break under sustained stress:
it's not the same to support stress for 5 seconds than for 5 hours.

-k: This does the "KeepAlive" functionality browsers do by nature. You don't need to pass a value for -k as it "
boolean" (meaning: it indicates that you desire for your test to use the Keep Alive header from HTTP and sustain the
connection). Since browsers do this and you're likely to want to simulate the stress and flow that your site will have
from browsers, it is recommended you do a benchmark with this.

The final argument is simply the host. By default, it will hit http:// protocol if you don't specify it.

```shell
 ab -k -c 350 -n 20000 http://localhost:8080/api/v1/names
```

### Demo for Api Key Rate Limiting

Let's call api/v1/rt/api-key endpoint using Free Plan api key first time:
Test API Key Rate Limiting

```shell
 curl --location 'http://localhost:8080/api/v1/rt/api-key' \
--header 'X-Api-Key: d0476978-free-4ad5-94e8-38ebb575f5c4'
```

Response:

```json
  {
  "key": "Api-Key-1cqdfFnP",
  "value": "Api-Key-EuQiXsFD"
}
```

Response:

```json
{
  "timestamp": "2025-01-22T00:52:47.693+00:00",
  "status": 429,
  "error": "Too Many Requests",
  "message": "You have exhausted your API Request Quota, please try again in [3281] seconds.",
  "path": "/api/v1/rt/api-key"
}
```

Basic Plan Rate Limiting

```shell
 curl --location 'http://localhost:8080/api/v1/rt/api-key' \
--header 'X-Api-Key: d0476978-base-4ad5-94e8-38ebb575f5c5' 
```

Response:

```json
{
  "key": "Api-Key-9wUzs8dt",
  "value": "Api-Key-tSjqGgdy"
}

```

### Demo for IP Rate Limiting

Let's call api/v1/rt/ip endpoint using Free Plan api key first time:

```shell
 curl --location 'http://localhost:8080/api/v1/rt/ip' \
--header 'X-Api-Key: d0476978-free-4ad5-94e8-38ebb575f5c4' 
```

Response:

```json
{
  "key": "Api-Key-1cqdfFnP",
  "value": "Api-Key-EuQiXsFD"
}
```

```shell
curl --location 'http://localhost:8080/api/v1/rt/ip' \
--header 'X-Api-Key: d0476978-base-4ad5-94e8-38ebb575f5c5'
```

Response:
```json
{
  "key": "Ip-C5nT7Sp9",
  "value": "Ip-fTiQwHhD"
}
```

```json
{
  "timestamp": "2025-01-22T01:24:53.391+00:00",
  "status": 429,
  "error": "Too Many Requests",
  "message": "You have exhausted your API Request Quota, please try again in [3406] seconds.",
  "path": "/api/v1/rt/ip"
}
```

We get Too Many Requests error and 429 error status code, because calculation of the requests is based on IP address
