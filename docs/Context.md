# Context of Application

We provide an api for a movies library based on a restful architecture. A context of use that we imagine is a studio that wants to manage it's own resources of movies, actors and their roles in it. So the user in this case would be the studio.

The users' activity are isolated from one to the other. That is, a studio for example cannot see the movies production value, revenue or any other details of another studio. This is a behaviour that we intended for the management of resources. We could go out and explore the possibility of opening up the barriers a bit between each studio's resources, but for now each studio has his own resources to create, to update, to delete, to manage, to do whatever it pleases with, within our api.