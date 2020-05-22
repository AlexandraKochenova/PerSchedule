using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;
using FeedMeServer.Models;

namespace FeedMeServer
{
    public class FeedMeContext : DbContext
    {
        public FeedMeContext()
            : base("DBConnection")
        { }

        public DbSet<Family> Families { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<Pet> Pets { get; set; }
        public DbSet<Responsibility> Responsibilities { get; set; }
        public DbSet<Food> Foods { get; set; }
        public DbSet<Thing> Things { get; set; }
    }
}