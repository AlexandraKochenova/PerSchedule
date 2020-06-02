namespace FeedMeServer.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddCorrectionsFriendshipMigration : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.FamilyUserRelations", "UserFirst_Id", c => c.Int());
            AddColumn("dbo.FamilyUserRelations", "UserSecond_Id", c => c.Int());
            CreateIndex("dbo.FamilyUserRelations", "UserFirst_Id");
            CreateIndex("dbo.FamilyUserRelations", "UserSecond_Id");
            AddForeignKey("dbo.FamilyUserRelations", "UserFirst_Id", "dbo.Users", "Id");
            AddForeignKey("dbo.FamilyUserRelations", "UserSecond_Id", "dbo.Users", "Id");
            DropColumn("dbo.FamilyUserRelations", "UserFirstID");
            DropColumn("dbo.FamilyUserRelations", "UserSecondID");
        }
        
        public override void Down()
        {
            AddColumn("dbo.FamilyUserRelations", "UserSecondID", c => c.Int(nullable: false));
            AddColumn("dbo.FamilyUserRelations", "UserFirstID", c => c.Int(nullable: false));
            DropForeignKey("dbo.FamilyUserRelations", "UserSecond_Id", "dbo.Users");
            DropForeignKey("dbo.FamilyUserRelations", "UserFirst_Id", "dbo.Users");
            DropIndex("dbo.FamilyUserRelations", new[] { "UserSecond_Id" });
            DropIndex("dbo.FamilyUserRelations", new[] { "UserFirst_Id" });
            DropColumn("dbo.FamilyUserRelations", "UserSecond_Id");
            DropColumn("dbo.FamilyUserRelations", "UserFirst_Id");
        }
    }
}
